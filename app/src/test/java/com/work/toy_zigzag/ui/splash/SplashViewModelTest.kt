package com.work.toy_zigzag.ui.splash

import com.work.toy_zigzag.data.repository.ShoppingRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @Mock
    private lateinit var shoppingRepository: ShoppingRepository

    @Mock
    private lateinit var splashViewModel: SplashViewModel


    @Before
    fun setup() {

        splashViewModel = SplashViewModel(shoppingRepository)
    }

    @Test
    fun test() {



        //이부분 고민해봐야함..
    }
}
