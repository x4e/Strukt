# Strukt
Value types on the JVM, today!

**ZERO** garbage, **ZERO** reflection, **ZERO** maps. **EASY** to use, and with **INCREDIBLE** performance.

[![Build Status](https://travis-ci.org/Jire/Strukt.svg?branch=master)](https://travis-ci.org/Jire/Strukt)
[![license](https://img.shields.io/github/license/Jire/Strukt.svg)](https://github.com/Jire/Strukt/blob/master/LICENSE.txt)

## Declaring a Strukt

The declaration syntax is very similar to regular classes, except:

* You should declare as an `object`, rather than `class`
* Members should be delegated by a subclass of `StruktMember`
* You must extend `Strukt`

For example, a Strukt for representing a coordinate might look like:

```kotlin
object Coord : Strukt() {
	val x by int()
	val y by int()
}
```

There are member delegates built in for all primitives besides _char_.

You can also specify a default value for your member, like so:

```kotlin
val x by int(3) // 3 is the default value
val y by int(defaultValue = 5) // 5 is the default value, shown with named arguments
```

You can also specify the size, in bytes, of the member to save space.

```kotlin
val x by int(3, 2) // 2 bytes size, allowing values 0 to 65535
val y by int(defaultValue = 3, size = 2) // showing with named arguments
```

## Allocating a reference

The syntax for allocation is a bit different than regular object construction.
Instead, you use the _invoke_ operator to set values.

For the above _Coord_ example, this might look like:

```kotlin
val threeFive = Coord { x = 3; y = 5 }
```

If you wanted to make use of default arguments, you can omit the sets.

```kotlin
val threeFive = Coord {}
```

Allocating a reference automatically sets the _reference pointer_. (More on this in the following section.)

## Accessing members

Accessing members is the farthest deviation from regular object syntax.

It is important to understand under the hood, _Strukt_ uses something called a _reference pointer_
to keep track of what reference is currently being worked on.

Switching to a reference pointer is done through the _get_ operator on the _Strukt_'s type.

This might look like this:

```kotlin
Coord[threeFive]
```

You can then deal with the type directly, which is _Coord_ in our example, like so:

```kotlin
Coord.y = 20
println("x: ${Coord[threeFive].x}, y: ${Coord[threeFive].y}") // prints "x: 3, y: 20"
```

Since the switch (_get_) operator switches the _reference pointer_, you can access
_Coord_ in our example directly by its object type. This might look like:

```kotlin
val threeFiveX = Coord.x
println("x: $threeFiveX")
```