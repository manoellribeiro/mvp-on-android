package com.raywenderlich.android.creaturemon.model

import org.junit.Before
import org.junit.Test

class CreatureGeneratorTest {

    private lateinit var creatureGenerator: CreatureGenerator

    @Before
    fun setUp() {
        creatureGenerator = CreatureGenerator()
    }

    @Test
    fun testGenerateHitPoints() {
        val attributes = CreatureAttributes(
                intelligence = 7,
                strength = 3,
                endurance = 10
        )

        val name = "Rikachu"
        val expectedCreature = Creature(attributes, 84, name)

        assert(expectedCreature == creatureGenerator.generateCreature(attributes, name))
    }

}