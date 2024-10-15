package com.hy.baseapp.ui.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.drake.net.utils.scopeDialog
import com.google.android.material.color.MaterialColors
import com.hy.baseapp.base.BaseActivity
import com.hy.baseapp.base.BaseComposeActivity
import com.hy.baseapp.base.PlanterThemeWithContent
import com.hy.baseapp.databinding.ActivityTestBinding
import me.hy.jetpackmvvm.base.viewmodel.BaseViewModel

class TestActivity : BaseComposeActivity<BaseViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        scopeDialog {  }
        setContent {
            PlanterThemeWithContent {
                Content()
            }
        }
    }

    @Preview
    @Composable
    fun Content() {
        Box(modifier = Modifier.fillMaxSize()) {
            TextButton(
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer
                ),
                onClick = {
                    finish()
                }, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp)
            ) {
                Text(text = "Compose Activity")
            }
        }
    }
}