package com.tymwitko.toreplace

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tymwitko.toreplace.list.CycleType
import com.tymwitko.toreplace.list.DeleteTaskUseCase
import com.tymwitko.toreplace.list.FetchTasksUseCase
import com.tymwitko.toreplace.list.Interval
import com.tymwitko.toreplace.list.Task
import com.tymwitko.toreplace.list.TaskListViewModel
import com.tymwitko.toreplace.list.TaskViewData
import com.tymwitko.toreplace.list.UpdateTaskUseCase
import com.tymwitko.toreplace.list.db.ChoreEntry
import com.tymwitko.toreplace.list.db.ChoreRepository
import com.tymwitko.toreplace.list.ui.TaskListUiState
import com.tymwitko.toreplace.stats.UpdateStatsUseCase
import com.tymwitko.toreplace.stats.db.StatsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import kotlin.time.Clock

class TaskListUnitTest {
  val choreRepo = mockk<ChoreRepository>(relaxed = true)
  val statsRepo = mockk<StatsRepository>(relaxed = true)
  val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  val stairsTask = Task(
    id = 420.toLong(),
    name = "Clean the stairs",
    description = "Someone dropped a lollipop",
    interval = Interval(7, CycleType.WEEKS),
    lastTimeDone = LocalDate.parse("2026-10-04")
  )

  @Before
  fun prepareTests() {
    coEvery { choreRepo.fetchSingleTask(any()) } returns ChoreEntry(stairsTask)
    coEvery { choreRepo.getAllChores() } returns listOf(stairsTask)
  }

  @Test
  fun fetchingTasksReturnsRepoContents() {
    runTest {
      val viewModel = getViewModel(testScheduler)
      viewModel.fetchTasks()
      advanceUntilIdle()
      assert(
        (viewModel.uiState.value as? TaskListUiState.Success)?.list
          ?.map { it.task } == listOf(stairsTask)
      )
    }
  }

  @Test
  fun fetchingTasksReturnsDaysLeft() {
    runTest {
      val daysLeft =
        stairsTask.interval.number * 7 - (today.toEpochDays() - stairsTask.lastTimeDone.toEpochDays()).toInt()
      val viewModel = getViewModel(testScheduler)
      viewModel.fetchTasks()
      advanceUntilIdle()
      assert(
        (viewModel.uiState.value as? TaskListUiState.Success)?.list
          ?.map { it.dueInDays } == listOf(daysLeft)
      )
    }
  }

  @Test
  fun resettingTimeSetsToday() {
    runTest {
      val viewModel = getViewModel(testScheduler)
      val daysLeft =
        stairsTask.interval.number * 7 - (today.toEpochDays() - stairsTask.lastTimeDone.toEpochDays()).toInt()
      viewModel.fetchTasks()
      advanceUntilIdle()
      viewModel.updateOnReset(TaskViewData(stairsTask, daysLeft), today)
      advanceUntilIdle()
      assert(
        (viewModel.uiState.value as? TaskListUiState.Success)?.list
          ?.map { it.dueInDays } == listOf(7 * stairsTask.interval.number)
      )
    }
  }

  @Test
  fun calculatingDaysLeftWorksForDays() {
    runTest {
      val lastDone = LocalDate.parse("2026-03-21")
      val interval = Interval(420, CycleType.DAYS)
      assert(
        getViewModel(testScheduler).getDaysLeft(lastDone, interval) == 196
      )
    }
  }

  @Test
  fun calculatingDaysLeftWorksForWeeks() {
    runTest {
      val lastDone = LocalDate.parse("2026-03-21")
      val interval = Interval(60, CycleType.WEEKS)
      assert(
        getViewModel(testScheduler).getDaysLeft(lastDone, interval) == 196
      )
    }
  }

  @Test
  fun calculatingDaysLeftWorksForMonths() {
    runTest {
      val lastDone = LocalDate.parse("2026-03-21")
      val interval = Interval(6, CycleType.MONTHS)
      assert(
        getViewModel(testScheduler).getDaysLeft(lastDone, interval) == -40
      )
    }
  }

  @Test
  fun calculatingDaysLeftWorksForYears() {
    runTest {
      val lastDone = LocalDate.parse("2026-03-21")
      val interval = Interval(2, CycleType.YEARS)
      assert(
        getViewModel(testScheduler).getDaysLeft(lastDone, interval) == 507
      )
    }
  }

  private fun getViewModel(testScheduler: TestCoroutineScheduler) = TaskListViewModel(
    FetchTasksUseCase(choreRepo),
    UpdateTaskUseCase(choreRepo),
    DeleteTaskUseCase(choreRepo),
    UpdateStatsUseCase(statsRepo),
    mockk(relaxed = true),
    StandardTestDispatcher(testScheduler)
  )
}
