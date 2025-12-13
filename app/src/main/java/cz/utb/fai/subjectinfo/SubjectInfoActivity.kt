package cz.utb.fai.subjectinfo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewmodel.viewModelFactory
import cz.utb.fai.subjectinfo.databinding.ActivitySubjectinfoBinding

class SubjectInfoActivity : AppCompatActivity() {


    // 1. Get the repository instance from the Application class
    private val repository by lazy { (application as MyApplication).repository }

    // 2. Create the factory using the repository from the Application class
    private val viewModelFactory by lazy { SubjectInfoViewModelFactory(repository) }

    // 3. Pass the factory to the viewModels() delegate
    private val viewModel: SubjectInfoViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            // MaterialTheme provides default typography and colors
            MaterialTheme {
                SubjectInfoScreen(viewModel = viewModel)
            }
        }


    }
}