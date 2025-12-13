package cz.utb.fai.subjectinfo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cz.utb.fai.subjectinfo.domain.SubjectInfoDomain

// 1. Stateful Screen (Connected to ViewModel) - DO NOT PREVIEW THIS DIRECTLY
@Composable
fun SubjectInfoScreen(
    viewModel: SubjectInfoViewModel = viewModel() // Usage in Activity
) {
    // Observe State
    val shortcutInput by viewModel.zkratkaMutable.observeAsState("")
    val subjectInfo by viewModel.subjectInfoDomainValue.observeAsState()
    val showHint by viewModel.showHint.observeAsState(false)
    val showNotFound by viewModel.showNotFound.observeAsState(false)

    // Pass state and events to the stateless composable
    SubjectInfoContent(
        shortcutInput = shortcutInput ?: "",
        subjectInfo = subjectInfo,
        showHint = showHint,
        showNotFound = showNotFound,
        onShortcutChange = { viewModel.zkratkaMutable.value = it },
        onSearchClick = { viewModel.search() }
    )
}

// 2. Stateless Content (Pure UI) - PREVIEW THIS
@Composable
fun SubjectInfoContent(
    shortcutInput: String,
    subjectInfo: SubjectInfoDomain?,
    showHint: Boolean,
    showNotFound: Boolean,
    onShortcutChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // --- Hint TextView ---
        if (showHint) {
            Text(
                text = stringResource(id = R.string.txtSubjectShorcut_hint),
                color = Color(0xFFF43682),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp, vertical = 5.dp)
            )
        }

        // --- EditText ---
        OutlinedTextField(
            value = shortcutInput,
            onValueChange = onShortcutChange,
            label = { Text(stringResource(id = R.string.txtSubject_hint)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClick() })
        )

        // --- Not Found TextView ---
        if (showNotFound) {
            Text(
                text = stringResource(id = R.string.subjectInfo_not_found),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 35.dp, top = 4.dp),
                color = MaterialTheme.colorScheme.error
            )
        }

        // --- Save Button ---
        Button(
            onClick = onSearchClick,
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Text(text = stringResource(id = R.string.btnSave_text))
        }

        Spacer(modifier = Modifier.height(30.dp))

        // --- Data Display ---
        subjectInfo?.let { info ->
            Text(
                text = info.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = info.credits.toString(),
                fontSize = 80.sp,
                modifier = Modifier.padding(top = 20.dp)
            )

            Text(
                text = info.shortcut,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 20.dp)
            )

            Text(
                text = info.department,
                color = Color(0xFFF44336),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}

// 3. Previews (No ViewModel required here)

@Preview(showBackground = true, name = "1. Empty State")
@Composable
fun PreviewSubjectInfoEmpty() {
    MaterialTheme {
        SubjectInfoContent(
            shortcutInput = "",
            subjectInfo = null,
            showHint = false,
            showNotFound = false,
            onShortcutChange = {},
            onSearchClick = {}
        )
    }
}

@Preview(showBackground = true, name = "2. Data Loaded")
@Composable
fun PreviewSubjectInfoData() {
    MaterialTheme {
        SubjectInfoContent(
            shortcutInput = "AK7MT",
            subjectInfo = SubjectInfoDomain(
                name = "Mobilní technologie",
                shortcut = "AK7MT",
                credits = 5,
                department = "UAI",
                description = "Example description"
            ),
            showHint = false,
            showNotFound = false,
            onShortcutChange = {},
            onSearchClick = {}
        )
    }
}

@Preview(showBackground = true, name = "3. Error/Not Found")
@Composable
fun PreviewSubjectInfoError() {
    MaterialTheme {
        SubjectInfoContent(
            shortcutInput = "UNKNOWN",
            subjectInfo = null,
            showHint = true, // Simulating empty input hint
            showNotFound = true, // Simulating API 404
            onShortcutChange = {},
            onSearchClick = {}
        )
    }
}
