package com.themoviedatabase.android.presentation.collections.presenter

import com.themoviedatabase.android.domain.model.colletions.MDBCollection
import com.themoviedatabase.android.domain.model.colletions.MDBCollectionCategory
import com.themoviedatabase.android.domain.repository.collections.CollectionRepository
import com.themoviedatabase.android.domain.usecases.collection.movies.GetMovieCollectionUseCase
import com.themoviedatabase.android.presentation.collections.view.CollectionView
import com.themoviedatabase.android.utils.CollectionMocks
import com.themoviedatabase.android.utils.CoroutineTestRule
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CollectionsLoadSuccessTest {
    
    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule(testDispatcher)


    @Mock
    private lateinit var view: CollectionView

    @Mock
    private lateinit var repository: CollectionRepository
    private lateinit var useCase: GetMovieCollectionUseCase
    private lateinit var presenter: CollectionPresenter

    @ExperimentalCoroutinesApi
    @Before
    fun before() {
        view =  mock(CollectionView::class.java)
        repository = mock(CollectionRepository::class.java)

        useCase = GetMovieCollectionUseCase(testDispatcher, repository)
        presenter = CollectionPresenter(testDispatcher, testDispatcher, useCase)

        presenter.attach(view)

        `when`(repository.getRecentMoviesCollection()).thenReturn(
            flow {
               emit(MDBResult.Loading)
               emit(MDBResult.Success(CollectionMocks.getMockListCollection()))
            }
        )

        `when`(repository.getMoviesPopularCollection()).thenReturn(
                flow {
                    emit(MDBResult.Loading)
                    emit(MDBResult.Success(CollectionMocks.getMockListCollection()))
                }
        )

        `when`(repository.getMoviesUpComingCollection()).thenReturn(
                flow {
                    emit(MDBResult.Loading)
                    emit(MDBResult.Success(CollectionMocks.getMockListCollection()))
                }
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadLatestSuccessCollectionTest()  {
        runBlockingTest {
            presenter.loadCollection(MDBCollectionCategory.Latest)
            verify(repository, only()).getRecentMoviesCollection()
            verify(view, never()).showRetry()
            verify(view, times(1)).showLoader(true)
            verify(view, times(1)).showLoader(false)
            verify(view, atLeastOnce()).showCollectionMovies(presenter.resultCollection)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadPopularSuccessCollectionTest()  {
        runBlockingTest {
            presenter.loadCollection(MDBCollectionCategory.Popular)
            verify(repository, only()).getMoviesPopularCollection()
            verify(view, never()).showRetry()
            verify(view, times(1)).showLoader(true)
            verify(view, times(1)).showLoader(false)
            verify(view, atLeastOnce()).showCollectionMovies(presenter.resultCollection)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadUpcomingSuccesCollectionTest()  {
        runBlockingTest {
            presenter.loadCollection(MDBCollectionCategory.UpComing)
            verify(repository, only()).getMoviesUpComingCollection()
            verify(view, never()).showRetry()
            verify(view, times(1)).showLoader(true)
            verify(view, times(1)).showLoader(false)
            verify(view, atLeastOnce()).showCollectionMovies(presenter.resultCollection)
        }
    }
}




