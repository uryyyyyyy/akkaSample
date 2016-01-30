package com.github.uryyyyyyy.samples.akka.sample.sequential

object MyCounter {
	private var value:Int = 0

	def add(i:Int): Unit ={
		println(s"add $i start")

		val currentVal = value
		Thread.sleep(100)
		value = currentVal + i

		println(s"add $i done")
	}

	def getVal(): Int ={
		value
	}

	def reset() ={
		value=0
	}
}