package com.hao.androidrecord.activity.expandable.expand01.college

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.hao.androidrecord.R
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollegeViewModel(application: Application, private val stateHandle: SavedStateHandle) :
    AndroidViewModel(application) {
    companion object {
        private const val ZONES = "zones"
    }

    val colleges = MutableLiveData<List<CollegeZone>>()

    init {
        val zones = stateHandle.get<List<CollegeZone>>(ZONES)
        if (zones != null) {
            colleges.value = zones
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    fun loadColleges() {
        GlobalScope.launch(Dispatchers.Main) {
            val zones = withContext(Dispatchers.IO) {
                val json = getApplication<Application>().resources
                    .openRawResource(R.raw.college)
                    .bufferedReader().readText()
                val collegeWrapper =
                    Moshi.Builder().build().adapter(CollegeWrapper::class.java).fromJson(json)!!
                val zoneMap = HashMap<String, CollegeZone>()
                for (zone in collegeWrapper.zone) {
                    zoneMap[zone.id] = zone
                }
                for (college in collegeWrapper.university) {
                    zoneMap[college.zone]?.colleges?.add(college)
                }
                for (zone in collegeWrapper.zone) {
                    zone.colleges.sortBy { it.order }
                }
                collegeWrapper.zone.sortedBy { it.sort }
            }
            colleges.value = zones
            stateHandle.set(ZONES, ArrayList(zones))
        }

    }


}
