package com.bluecapsystem.lotte.illywa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluecapsystem.lotte.illywa.sample.Sample
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		// call java object
		val sample = Sample()
		val str = sample.getString("bluecap")

		txt_message.text = str


	}
}
