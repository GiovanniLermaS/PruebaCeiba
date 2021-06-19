package co.com.ceiba.mobile.pruebadeingreso

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import co.com.ceiba.mobile.pruebadeingreso.utils.ViewModelFactory
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.MainActivityViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var mainActivityViewModel: MainActivityViewModel? = null

    @Before
    fun setUp() {
        val component = DaggerTestApplicationComponent
                .builder()
                .build()
        component.inject(this)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals("co.com.ceiba.mobile.pruebadeingreso", appContext.packageName)
    }
}