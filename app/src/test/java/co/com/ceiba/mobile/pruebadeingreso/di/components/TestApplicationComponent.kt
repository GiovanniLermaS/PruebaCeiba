package co.com.ceiba.mobile.pruebadeingreso.di.components

import co.com.ceiba.mobile.pruebadeingreso.ExampleUnitTest
import co.com.ceiba.mobile.pruebadeingreso.di.component.ApplicationComponent

interface TestAppComponent : ApplicationComponent {
    fun inject(exampleUnitTest: ExampleUnitTest)
}