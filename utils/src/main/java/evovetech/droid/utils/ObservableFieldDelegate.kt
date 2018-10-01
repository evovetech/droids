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

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal
class ObservableFieldDelegate<R : ObservableNotifier, T>(
    private val fieldId: Int,
    private var value: T
) : ReadWriteProperty<R, T> {
    override
    fun getValue(
        thisRef: R,
        property: KProperty<*>
    ): T = value

    override
    fun setValue(
        thisRef: R,
        property: KProperty<*>,
        value: T
    ) {
        this.value = value
        thisRef.notifyPropertyChanged(fieldId)
    }
}

fun <R : ObservableNotifier, T : Any> R.observableField(
    fieldId: Int,
    initialValue: T
): ReadWriteProperty<R, T> = ObservableFieldDelegate(
    fieldId,
    initialValue
)
