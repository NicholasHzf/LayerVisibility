package com.layout.visibility

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.layout.visibility.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        IndexStateManager.initViewMap(mutableListOf(
            binding.tvAuthorName,binding.tvAuthorIntroduction,
            binding.tvToolBox,binding.tvFolder,
            binding.ivZoomIn,binding.ivZoomOut,
            binding.ivClose,binding.ivAndroid,
            binding.tvTabFirst,binding.tvTabSecond,binding.tvTabThird
        ))

        binding.tvChangeState.setOnClickListener {
            IndexStateManager.changeStateRandom()
        }

        binding.tvAddClose.setOnClickListener {
            IndexStateManager.showView(IndexStateManager.INDEX_VIEW_CLOSE)
        }

        binding.tvReduceClose.setOnClickListener {
            IndexStateManager.hideView(IndexStateManager.INDEX_VIEW_CLOSE)
        }
    }
}