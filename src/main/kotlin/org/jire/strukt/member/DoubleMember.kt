/*
 *    Copyright 2016-2017 Thomas G. Nappo (Jire)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package org.jire.strukt.member

import org.jire.strukt.Strukt
import org.jire.strukt.unsafe
import kotlin.reflect.KProperty

/**
 * A [StruktMember] which delegates [Double]s.
 *
 * @param strukt The parent [Strukt] of this member, to which this member belongs to.
 * @param defaultValue The default value of this member.
 */
class DoubleMember(strukt: Strukt, val defaultValue: Double) : StruktMember(strukt, 8) {
	
	init {
		offset = strukt.internalPointer
		strukt.internalPointer += size
		strukt.members.add(this)
	}
	
	/**
	 * Gets the value of this [DoubleMember].
	 */
	fun get() = unsafe.getDouble(pointer())
	
	/**
	 * Sets the value of this [DoubleMember] to the specified value.
	 *
	 * @param value The new value.
	 */
	fun set(value: Double) = unsafe.putDouble(pointer(), value)
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Double) = set(value)
	
	override fun writeDefault() = set(defaultValue)
	
	override fun toString(): String = java.lang.Double.toString(get())
	
}