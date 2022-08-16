package com.cw.catapoke.mock

interface MockResponse {
    fun getMockData():String
}

object SpeicesMockResponse : MockResponse {
    override fun getMockData(): String {
       return """
        {"count":905,"next":"https://pokeapi.co/api/v2/pokemon-species?offset=20&limit=20","previous":null,"results":[{"name":"bulbasaur","url":"https://pokeapi.co/api/v2/pokemon-species/1/"},{"name":"ivysaur","url":"https://pokeapi.co/api/v2/pokemon-species/2/"},{"name":"venusaur","url":"https://pokeapi.co/api/v2/pokemon-species/3/"},{"name":"charmander","url":"https://pokeapi.co/api/v2/pokemon-species/4/"},{"name":"charmeleon","url":"https://pokeapi.co/api/v2/pokemon-species/5/"},{"name":"charizard","url":"https://pokeapi.co/api/v2/pokemon-species/6/"},{"name":"squirtle","url":"https://pokeapi.co/api/v2/pokemon-species/7/"},{"name":"wartortle","url":"https://pokeapi.co/api/v2/pokemon-species/8/"},{"name":"blastoise","url":"https://pokeapi.co/api/v2/pokemon-species/9/"},{"name":"caterpie","url":"https://pokeapi.co/api/v2/pokemon-species/10/"},{"name":"metapod","url":"https://pokeapi.co/api/v2/pokemon-species/11/"},{"name":"butterfree","url":"https://pokeapi.co/api/v2/pokemon-species/12/"},{"name":"weedle","url":"https://pokeapi.co/api/v2/pokemon-species/13/"},{"name":"kakuna","url":"https://pokeapi.co/api/v2/pokemon-species/14/"},{"name":"beedrill","url":"https://pokeapi.co/api/v2/pokemon-species/15/"},{"name":"pidgey","url":"https://pokeapi.co/api/v2/pokemon-species/16/"},{"name":"pidgeotto","url":"https://pokeapi.co/api/v2/pokemon-species/17/"},{"name":"pidgeot","url":"https://pokeapi.co/api/v2/pokemon-species/18/"},{"name":"rattata","url":"https://pokeapi.co/api/v2/pokemon-species/19/"},{"name":"raticate","url":"https://pokeapi.co/api/v2/pokemon-species/20/"}]}
        """
    }
}

object SpeciesEvolutionResponse : MockResponse {
    override fun getMockData(): String {
       return """{"baby_trigger_item":null,"chain":{"evolution_details":[],"evolves_to":[{"evolution_details":[{"gender":null,"held_item":null,"item":null,"known_move":null,"known_move_type":null,"location":null,"min_affection":null,"min_beauty":null,"min_happiness":null,"min_level":16,"needs_overworld_rain":false,"party_species":null,"party_type":null,"relative_physical_stats":null,"time_of_day":"","trade_species":null,"trigger":{"name":"level-up","url":"https://pokeapi.co/api/v2/evolution-trigger/1/"},"turn_upside_down":false}],"evolves_to":[{"evolution_details":[{"gender":null,"held_item":null,"item":null,"known_move":null,"known_move_type":null,"location":null,"min_affection":null,"min_beauty":null,"min_happiness":null,"min_level":32,"needs_overworld_rain":false,"party_species":null,"party_type":null,"relative_physical_stats":null,"time_of_day":"","trade_species":null,"trigger":{"name":"level-up","url":"https://pokeapi.co/api/v2/evolution-trigger/1/"},"turn_upside_down":false}],"evolves_to":[],"is_baby":false,"species":{"name":"venusaur","url":"https://pokeapi.co/api/v2/pokemon-species/3/"}}],"is_baby":false,"species":{"name":"ivysaur","url":"https://pokeapi.co/api/v2/pokemon-species/2/"}}],"is_baby":false,"species":{"name":"bulbasaur","url":"https://pokeapi.co/api/v2/pokemon-species/1/"}},"id":1}"""
    }
}

object SpeicesDetailsMockResponse : MockResponse {
    override fun getMockData(): String {
        return """{
  "base_happiness": 50,
  "capture_rate": 45,
  "color": {
    "name": "green",
    "url": "https://pokeapi.co/api/v2/pokemon-color/5/"
  },
  "egg_groups": [
    {
      "name": "monster",
      "url": "https://pokeapi.co/api/v2/egg-group/1/"
    },
    {
      "name": "plant",
      "url": "https://pokeapi.co/api/v2/egg-group/7/"
    }
  ],
  "evolution_chain": {
    "url": "https://pokeapi.co/api/v2/evolution-chain/1/"
  },
  "evolves_from_species": null,
  "flavor_text_entries": [
    {
      "flavor_text": "A strange seed was\nplanted on its\nback at birth.\fThe plant sprouts\nand grows with\nthis POKéMON.",
      "language": {
        "name": "en",
        "url": "https://pokeapi.co/api/v2/language/9/"
      },
      "version": {
        "name": "red",
        "url": "https://pokeapi.co/api/v2/version/1/"
      }
    },
    {
      "flavor_text": "A strange seed was\nplanted on its\nback at birth.\fThe plant sprouts\nand grows with\nthis POKéMON.",
      "language": {
        "name": "en",
        "url": "https://pokeapi.co/api/v2/language/9/"
      },
      "version": {
        "name": "blue",
        "url": "https://pokeapi.co/api/v2/version/2/"
      }
    }
  ],
  "form_descriptions": [],
  "forms_switchable": false,
  "gender_rate": 1,
  "genera": [
    {
      "genus": "Seed Pokémon",
      "language": {
        "name": "en",
        "url": "https://pokeapi.co/api/v2/language/9/"
      }
    }
  ],
  "generation": {
    "name": "generation-i",
    "url": "https://pokeapi.co/api/v2/generation/1/"
  },
  "growth_rate": {
    "name": "medium-slow",
    "url": "https://pokeapi.co/api/v2/growth-rate/4/"
  },
  "habitat": {
    "name": "grassland",
    "url": "https://pokeapi.co/api/v2/pokemon-habitat/3/"
  },
  "has_gender_differences": false,
  "hatch_counter": 20,
  "id": 1,
  "is_baby": false,
  "is_legendary": false,
  "is_mythical": false,
  "name": "bulbasaur",
  "names": [
    {
      "language": {
        "name": "en",
        "url": "https://pokeapi.co/api/v2/language/9/"
      },
      "name": "Bulbasaur"
    }
  ],
  "order": 1,
  "pal_park_encounters": [
    {
      "area": {
        "name": "field",
        "url": "https://pokeapi.co/api/v2/pal-park-area/2/"
      },
      "base_score": 50,
      "rate": 30
    }
  ],
  "pokedex_numbers": [
    {
      "entry_number": 1,
      "pokedex": {
        "name": "national",
        "url": "https://pokeapi.co/api/v2/pokedex/1/"
      }
    },
    {
      "entry_number": 1,
      "pokedex": {
        "name": "kanto",
        "url": "https://pokeapi.co/api/v2/pokedex/2/"
      }
    },
    {
      "entry_number": 226,
      "pokedex": {
        "name": "original-johto",
        "url": "https://pokeapi.co/api/v2/pokedex/3/"
      }
    },
    {
      "entry_number": 231,
      "pokedex": {
        "name": "updated-johto",
        "url": "https://pokeapi.co/api/v2/pokedex/7/"
      }
    },
    {
      "entry_number": 80,
      "pokedex": {
        "name": "kalos-central",
        "url": "https://pokeapi.co/api/v2/pokedex/12/"
      }
    },
    {
      "entry_number": 1,
      "pokedex": {
        "name": "letsgo-kanto",
        "url": "https://pokeapi.co/api/v2/pokedex/26/"
      }
    },
    {
      "entry_number": 68,
      "pokedex": {
        "name": "isle-of-armor",
        "url": "https://pokeapi.co/api/v2/pokedex/28/"
      }
    }
  ],
  "shape": {
    "name": "quadruped",
    "url": "https://pokeapi.co/api/v2/pokemon-shape/8/"
  },
  "varieties": [
    {
      "is_default": true,
      "pokemon": {
        "name": "bulbasaur",
        "url": "https://pokeapi.co/api/v2/pokemon/1/"
      }
    }
  ]
}
""" }


}


