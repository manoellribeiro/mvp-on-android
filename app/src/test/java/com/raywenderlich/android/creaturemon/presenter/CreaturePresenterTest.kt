package com.raywenderlich.android.creaturemon.presenter

import com.raywenderlich.android.creaturemon.model.*
import com.raywenderlich.android.creaturemon.presenter.creature.CreatureContract
import com.raywenderlich.android.creaturemon.presenter.creature.CreaturePresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CreaturePresenterTest {

    private lateinit var presenter: CreaturePresenter

    @Mock
    lateinit var mockedView: CreatureContract.View

    @Mock
    lateinit var mockGenerator: CreatureGenerator

    @Mock
    lateinit var mockRepository: CreatureRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CreaturePresenter(mockGenerator, mockRepository)
        presenter.setView(mockedView)
    }

    @Test
    fun testIntelligenceSelected() {
        val attributes = CreatureAttributes(10, 0, 0)
        val stubCreature = Creature(attributes, 50)
        `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

        presenter.attributeSelected(AttributeType.INTELLIGENCE, 3)

        verify(mockedView, times(1)).showHitPoints("50")
    }

    @Test
    fun testEnduranceSelected() {
        val attributes = CreatureAttributes(0, 0, 10)
        val stubCreature = Creature(attributes, 40)
        `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

        presenter.attributeSelected(AttributeType.ENDURANCE, 3)

        verify(mockedView, times(1)).showHitPoints("40")
    }

    @Test
    fun testStrengthSelected() {
        val attributes = CreatureAttributes(0, 10, 0)
        val stubCreature = Creature(attributes, 30)
        `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

        presenter.attributeSelected(AttributeType.STRENGTH, 3)

        verify(mockedView, times(1)).showHitPoints("30")
    }
}