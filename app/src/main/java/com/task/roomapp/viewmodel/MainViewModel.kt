package com.task.roomapp.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.task.roomapp.R
import com.task.roomapp.ResourcesProvider
import com.task.roomapp.RoomsRepository
import com.task.roomapp.model.Rooms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.TimeZone
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val roomsRepository: RoomsRepository,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    val getAllRooms: LiveData<MutableList<Rooms>> = roomsRepository.allRooms.asLiveData()

    suspend fun insert(roomsItem: Rooms) = roomsRepository.insert(roomsItem)

    suspend fun update(rooms: Rooms) = roomsRepository.update(rooms)

    suspend fun isDataExist(name: String?): Rooms? = roomsRepository.isDataExist(name)

    val _dismissDialogEvent = MutableLiveData<Boolean>()
    val dismissDialogEvent: LiveData<Boolean> = _dismissDialogEvent

    fun requestDismissDialog() {
        _dismissDialogEvent.value = true
    }


    var text = ObservableField<String>("")
    var isChecked = ObservableField<Boolean>(false)
    var update = ObservableField<Boolean>(false)
    var id = ObservableField<Long>()
    val showToastMessage = MutableLiveData<String>()

    fun getTextValue(): String {
        return text.get().toString()
    }

    fun getCheck(): Boolean {
        return isChecked.get()!!
    }

    fun insertData() {
        if (!update.get()!!) {
            if (text.get().toString().trim() != "") {
                val currentTimeMillis = System.currentTimeMillis()
                val timeZoneOffsetMillis = TimeZone.getDefault().getOffset(currentTimeMillis)
                val localTimeMillis = currentTimeMillis + timeZoneOffsetMillis
                val rooms = Rooms(0, text.get().toString(), isChecked.get()!!, localTimeMillis)
                CoroutineScope(Dispatchers.IO).launch {
                    if (isDataExist(text.get().toString()) == null) {
                        insert(rooms)
                        withContext(Dispatchers.Main) {
                            _dismissDialogEvent.value = true
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            showToastMessage.value =
                                resourcesProvider.getString(R.string.already_exist)
                        }
                    }

                }
            } else {
                showToastMessage.value = resourcesProvider.getString(R.string.enter_room)
            }
        } else {
            val currentTimeMillis = System.currentTimeMillis()
            val timeZoneOffsetMillis = TimeZone.getDefault().getOffset(currentTimeMillis)
            val localTimeMillis = currentTimeMillis + timeZoneOffsetMillis
            val rooms = Rooms(id.get()!!, text.get().toString(), isChecked.get()!!, localTimeMillis)
            CoroutineScope(Dispatchers.IO).launch {
                update(rooms)
                withContext(Dispatchers.Main) {
                    _dismissDialogEvent.value = true
                }
            }
        }
    }
}