package cz.utb.fai.subjectinfo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewmodel.viewModelFactory
import cz.utb.fai.subjectinfo.databinding.ActivitySubjectinfoBinding

class SubjectInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubjectinfoBinding

    // 1. Get the repository instance from the Application class
    private val repository by lazy { (application as MyApplication).repository }

    // 2. Create the factory using the repository from the Application class
    private val viewModelFactory by lazy { SubjectInfoViewModelFactory(repository) }

    // 3. Pass the factory to the viewModels() delegate
    private val viewModel: SubjectInfoViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // inicializace binding
        binding = ActivitySubjectinfoBinding.inflate(layoutInflater)
        // nastavení layoutu
        setContentView(binding.root)
        // propojení VM s XML UI
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}