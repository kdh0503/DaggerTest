package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Application
import dagger.Component


// Definition of the Application graph
@Component
interface ApplicationComponent {
    fun inject(myActivity: MainActivity)
}

// appComponent lives in the Application class to share its lifecycle
class MyApplication : Application() {
    // Reference to the application graph that is used across the whole app
    var appComponent: ApplicationComponent = DaggerApplicationComponent.create()

}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}