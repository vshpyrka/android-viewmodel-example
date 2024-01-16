# android-viewmodel-example

This is the set of examples of ViewModel APIs:

[ViewModelFactoryActivity](ViewModelFactoryActivity) - Demonstrates how to create ViewModel using _viewModelFactory_ delegate.

```
 // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Get application instance by key
                val application = (this[APPLICATION_KEY] as Application)

```

[ScopedViewModelActivity](https://github.com/vshpyrka/android-viewmodel-example/blob/main/src/main/java/com/example/viewmodel/ScopedViewModelActivity.kt) - Shows how ViewModel can be scoped to a parent fragments and used as shared ViewModel across different nested fragments.

```
class NestedFragment : Fragment() {

    // ViewModel API available in fragment.fragment-ktx
    // The ViewModel is scoped to the parent of the current Fragment
    private val viewModel: SharedViewModel by viewModels(
        ownerProducer = {
            requireParentFragment()
        }
    )
```
[ViewModelSavedStateHandleActivity](https://github.com/vshpyrka/android-viewmodel-example/blob/main/src/main/java/com/example/viewmodel/ViewModelSavedStateHandleActivity.kt) - Provides example of how data can be passed to ViewModel's SavedStateHandle storage object, and observed as Kotlin Coroutine Flow, with react to the incoming data.

```
val filteredData: Flow<List<String>> =
        savedStateHandle.getStateFlow("query", "")
            .flatMapLatest { query ->
                flow {
                    emit(
                        cheeses.filter {
                            it.contains(query, ignoreCase = true)
                        }
                    )
                }
            }
```

Example:

https://github.com/vshpyrka/android-viewmodel-example/assets/2741602/ad9c307a-247f-4605-9056-106a4eafd695

[ViewModelSavedStateHandleActivityTest](https://github.com/vshpyrka/android-viewmodel-example/blob/main/src/androidTest/java/com/example/viewmodel/ViewModelSavedStateHandleActivityTest.kt) - Shows how to run instrumentation test to validate SavedStateHandle behavior

More information about [SavedStateHandle](https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-savedstate)
