package com.dat.jetpackcomposecatalog.presentation.feature.main.home


//
//@ExperimentalCoroutinesApi
//class HomeViewModelTest {
//    @get:Rule
//    val mainDispatcherRule = MainDispatcherRule()
//    private lateinit var viewModel: HomeViewModel
//    private lateinit var fakeRepository: FakeTodoRepository
//
//    @Before
//    fun setup() {
//        fakeRepository = FakeTodoRepository()
//        viewModel = HomeViewModel(fakeRepository)
//    }
//
//    @After
//    fun tearDown() {
//    }
//
//    @Test
//    fun `refreshData should update curPage value and call getTodos`() = runTest {
//        // Given
//        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.homeUiState.collect() }
//
//        fakeRepository.createInitFlow()
//        fakeRepository.loading()
//
//        viewModel.refreshData()
//
//        assertEquals(1, viewModel.curPage)
//
//        val todoItems = fakeRepository.success()
//        // after success + curPageValue will increase 1
//        assertEquals(2, viewModel.curPage)
//        assertEquals(todoItems, viewModel.homeUiState.value.listTodoItem)
//
//        collectJob1.cancel()
//    }
//
//    @Test
//    fun `getTodos should emit loading state followed by success state with data`() =
//        runTest {
//            val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.homeUiState.collect() }
//            // Given
//            fakeRepository.createInitFlow()
//            // When
//            viewModel.refreshData()
//
//            // loading
//            assertEquals(true, viewModel.homeUiState.value.isLoading)
//            // success
//            val todoItems = fakeRepository.success()
//
//            assertEquals(false, viewModel.homeUiState.value.isLoading)
//            assertEquals(todoItems, viewModel.homeUiState.value.listTodoItem)
//
//            collectJob1.cancel()
//        }
//
//    @Test
//    fun `getTodos should emit loading state followed by error state with message`() = runTest {
//        // Given
//        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.homeUiState.collect() }
//        // Given
//        fakeRepository.createInitFlow()
//        // When
//        viewModel.refreshData()
//
//        // loading
//        assertEquals(true, viewModel.homeUiState.value.isLoading)
//        // fail
//        val error = fakeRepository.error()
//
//        assertEquals(false, viewModel.homeUiState.value.isLoading)
//        assertEquals(error.message, viewModel.homeUiState.value.errorMessage)
//
//        collectJob1.cancel()
//    }
//}
//
