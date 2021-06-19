package co.com.ceiba.mobile.pruebadeingreso.di.components

import co.com.ceiba.mobile.pruebadeingreso.ExampleInstrumentedTest
import co.com.ceiba.mobile.pruebadeingreso.di.component.ApplicationComponent

interface TestApplicationComponent : ApplicationComponent {
    fun inject(exampleInstrumentedTest: ExampleInstrumentedTest)
}