package com.example.discogs.common

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
abstract class BaseTest {
  @get:Rule
  val trampolineSchedulerRule = TrampolineSchedulerRule()

  @get:Rule
  var rule = InstantTaskExecutorRule()

  @get:Rule
  val mockitoRule: MockitoRule = MockitoJUnit.rule()
}
