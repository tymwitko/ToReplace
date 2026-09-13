package com.tymwitko.toreplace.newtask.ui

import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.core.text.isDigitsOnly

class DigitOnlyInputTransformation : InputTransformation {
  override fun TextFieldBuffer.transformInput() {
    if (!asCharSequence().isDigitsOnly()) {
      revertAllChanges()
    }
  }
}
