package com.bluecapsystem.lotte.illywa

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bluecapsystem.lotte.illywa.edl.EDLBuilder
import com.bluecapsystem.lotte.illywa.edl.utils.TimeUtils
import com.bluecapsystem.lotte.illywa.sample.Sample

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		// call java object
		val sample = Sample()
		val str = sample.getString("bluecap")

		val edl = EDLBuilder()


		val dur = (48 * 1000 * 3600) + (55 * 60000) + (12 * 1000) + 118;
		Log.d("matin", dur.toString())

		var timecode = TimeUtils.toTimeCode(dur.toLong()).toString()
		Log.d("matin", timecode.toString())


		Log.d("matin", TimeUtils.toLong(timecode.toString()).toString())


	}
}
