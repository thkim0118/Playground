package com.thkim.playground.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import com.google.android.material.snackbar.Snackbar
import com.thkim.playground.base.BaseActivity
import com.thkim.playground.databinding.ActivityOneBinding

class OneActivity : BaseActivity<ActivityOneBinding>(ActivityOneBinding::inflate) {

    private val twoActivityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.d("terry", "twoActivityLauncher : ${it.data}, ${it.resultCode}")
        }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            it.entries.forEach { entry ->
                Log.d("terry", "test -> ${entry.key}, ${entry.value}")
            }
            Snackbar.make(binding.root, "completed.", Snackbar.LENGTH_LONG).show()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btMove.setOnClickListener {
            val intent =
                Intent(this, Class.forName("com.thkim.playground.activity.TwoActivity")).apply {
                    putExtras(bundleOf(Pair("test", 9090)))
                }
            twoActivityLauncher.launch(intent)
        }

        binding.btPermission.setOnClickListener {
            requestPermission.launch(
                arrayOf(
                    android.Manifest.permission_group.STORAGE,
                    android.Manifest.permission.RECORD_AUDIO
                )
            )
        }
    }
}