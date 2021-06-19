package co.com.ceiba.mobile.pruebadeingreso

import co.com.ceiba.mobile.pruebadeingreso.di.components.TestAppComponent
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ExampleUnitTest {

    @Before
    fun setUp() {
        val component: TestAppComponent = DaggerTestApplicationComponent
                .builder()
                .build()
        component.inject(this)
    }

    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }
}