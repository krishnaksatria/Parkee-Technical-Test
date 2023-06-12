package com.id.parkee.commons.ui.dialog

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.id.parkee.commons.R
import com.id.parkee.commons.databinding.LayoutBottomSheetBinding

class BottomSheetDialog(context: Context) :
    BottomSheetDialog(context, R.style.AppBottomSheetDialogTheme) {

    private var binding: LayoutBottomSheetBinding =
        LayoutBottomSheetBinding.inflate(layoutInflater, null, false)

    init {
        setContentView(binding.root)
    }
}
