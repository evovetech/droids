/*
 * Copyright 2018 evove.tech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package evovetech.droid.utils

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry

/**
 * A ViewModel that is also an Observable,
 * to be used with the Data Binding Library.
 */
open
class ObservableViewModel : ViewModel(),
    Observable,
    ObservableNotifier {

    private
    val callbacks = PropertyChangeRegistry()

    override
    fun addOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ): Unit = callbacks.add(callback)

    override
    fun removeOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ): Unit = callbacks.remove(callback)

    override
    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    override
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}
