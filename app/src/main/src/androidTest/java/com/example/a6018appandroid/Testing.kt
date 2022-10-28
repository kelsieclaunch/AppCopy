import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import com.example.a6018appandroid.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest


@RunWith(AndroidJUnit4::class)
class DatabaseTesting : TestCase() {
    private lateinit var userDao: UserDAO
    private lateinit var db: AppDatabase
    @Rule
    @JvmField
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TestDatabase::class.java).build()
        userDao = db.getDatabase()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val user: User = TestUtil.createUser(3).apply {
            setName("george")
        }
        userDao.insert(user)
        val byName = userDao.getName("george")
        assertThat(byName.get(0), equalTo(user))
    }

    @Test
    fun insertUser() = runBlockingTest {
        val userEntity = User(1, "Jeff", 26, "Moderately Active", "60", "Male", 145)
        userDao.insert(userEntity)
        val users = userDao.getAll().getOrAwaitValue()
        assertThat(users).contains(userEntity)
    }

    @Test
    fun updateUser() = runBlockingTest {
        val userEntity = User(1, "Jeff", 26, "Moderately Active", "60", "Male", 145)
        userDao.insert(userEntity)
        val newUser = userEntity.copy(name = "Sally")
        userDao.update(newUser)
        val users = userDao.getUsersList().getOrAwaitValue()
        assertThat(users).contains(newUser)
    }

}
