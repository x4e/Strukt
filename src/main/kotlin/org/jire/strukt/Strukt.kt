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

package org.jire.strukt

import org.jire.strukt.member.StruktMember

/**
 * A _Strukt_, or more recognizably a _structure_, is a data type
 * that represents a group of members (see: [StruktMember]).
 *
 * An example _Strukt_ definition might look like this:
 *
 * ```kotlin
 * object Point : Strukt() {
 *     val x by int()
 *     val y by int()
 * }
 * ```
 */
abstract class Strukt {
	
	companion object {
		
		/**
		 * A pointer that references nothing.
		 */
		const val NIL = -1L
		
		/**
		 * The initial size of a strukt. This is by default 0.
		 */
		private const val INITIAL_SIZE = 0L
		
		/**
		 * The initial pointer of a strukt. This is by default [NIL].
		 */
		private const val INITIAL_POINTER = NIL
		
		/**
		 * The initial internal pointer of a strukt. This is by default 0.
		 */
		private const val INITIAL_INTERNAL_POINTER = 0L
		
		/**
		 * The initial pointer to the default strukt instance. This is by default [NIL].
		 */
		private const val INITIAL_DEFAULT_POINTER = NIL
		
	}
	
	/**
	 * The pointer to the active reference.
	 */
	var pointer = INITIAL_POINTER
	
	/**
	 * The internal pointer of the strukt.
	 *
	 * WARNING: This value does not get updated past the first allocation.
	 */
	var internalPointer = INITIAL_INTERNAL_POINTER
	
	/**
	 * The pointer to the default reference, which houses
	 * the default values of this strukt's members.
	 */
	var defaultPointer = INITIAL_DEFAULT_POINTER
	
	/**
	 * The total size, in bytes, of the structure.
	 */
	var size = INITIAL_SIZE
	
	/**
	 * A set of [StruktMember]s that belong to this strukt.
	 */
	val members: MutableSet<StruktMember> = HashSet()
	
	/**
	 * Adjusts the view of this [Strukt] to match the specified [pointer].
	 *
	 * @param pointer The new reference pointer, which you can get from a [Strukt] allocation.
	 */
	operator fun get(pointer: Long) = apply {
		this.pointer = pointer
	}
	
}