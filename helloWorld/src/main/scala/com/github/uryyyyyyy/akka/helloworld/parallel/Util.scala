package com.github.uryyyyyyy.akka.helloworld.parallel

object Util {

	def heavyMethod(i:Int) :Int = {
		Thread.sleep(200)
		println(s"heavyMethod result. - $i")
		i
	}

}