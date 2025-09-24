class Hero:
    """Basic template for hero. Contains name, power level and health points attributes. Implements the punch methods and string method."""

    def __init__(self, name="", power_level=1, health_points=100):
        self.__name = name
        self.health_points = health_points
        self.power_level = power_level

    def punch(self) -> float:
        """Return the punch power, which is 2 times the hero's level"""
        return self.power_level * 2

    def __str__(self):
        hero_info = f"Name: {self.__name}\n"
        hero_info += f"Power level: {self.power_level}\n"
        hero_info += f"Health points: {self.health_points}\n"
        return hero_info

class Paladin(Hero):
    """Paladin class extends Hero. Includes divine favor attribute and methods for holy strike and divine shield."""

    def __init__(self, name="", power_level=1, health_points=100, divine_favor=1):
        super().__init__(name, power_level, health_points)
        self.divine_favor = divine_favor

    def holy_strike(self) -> float:
        """Return the attack power of the holy strike, which is 3 times the hero's power level plus divine favor."""
        return self.power_level * 3 + self.divine_favor

    def divine_shield(self):
        """Increase health points by an amount equal to divine favor times 5."""
        self.health_points += self.divine_favor * 5

    def combat(self, monster):
        """Combat method to fight a monster."""
        if not isinstance(monster, Monster):
            print("The hero only fights monsters.")
            return

        print(f"Combat started between {self} and {monster}")

        while self.health_points > 0 and monster.health_points > 0:
            # Hero attacks first
            monster.health_points -= self.holy_strike()
            if monster.health_points <= 0:
                print(f"{monster.name} has been defeated by the hero!")
                return True

            # Monster attacks second
            self.health_points -= monster.attack()
            if self.health_points <= 0:
                print(f"{self.__class__.__name__} has been defeated by the monster!")
                return False

        # This point should never be reached
        return self.health_points > 0

    def __str__(self):
        hero_info = super().__str__()
        hero_info += f"Divine Favor: {self.divine_favor}\n"
        return hero_info

class Monster:
    """Monster class with attributes for name, strength, health points, and roar.
    Includes methods for attack, string representation, and addition."""

    def __init__(self, name: str, strength: float, health_points: float, roar: str):
        self.name = name
        self.strength = strength
        self.health_points = health_points
        self.roar = roar
        print(self.roar)

    def attack(self) -> float:
        """Return the attack power, which is equal to the monster's strength."""
        return self.strength

    def __str__(self):
        monster_info = f"Name: {self.name}\n"
        monster_info += f"Strength: {self.strength}\n"
        monster_info += f"Health points: {self.health_points}\n"
        monster_info += f"Roar: {self.roar}\n"
        return monster_info

    def __add__(self, other):
        """Combine two monsters into a new one with concatenated names, multiplied strengths,
        summed health points, and combined roars."""
        if not isinstance(other, Monster):
            return NotImplemented

        new_name = self.name + other.name
        new_strength = self.strength * other.strength
        new_health_points = self.health_points + other.health_points
        new_roar = self.roar + other.roar + "!!!"

        return Monster(new_name, new_strength, new_health_points, new_roar)

# Example usage for Tasks 4 and 5
if __name__ == "__main__":
    # Create an instance of the Paladin class
    paladin = Paladin(name="Arthur", power_level=5, health_points=150, divine_favor=10)
    print(paladin)

    # Create two instances of the Monster class
    monster1 = Monster(name="Gorgon", strength=50.0, health_points=200.0, roar="Raaaaar!")
    monster2 = Monster(name="Hydra", strength=30.0, health_points=150.0, roar="Hissss!")

    # Combine the two monsters into a monstrosity
    monstrosity = monster1 + monster2
    print(monstrosity)

    # Hero combats the monsters one at a time
    monsters = [monster1, monster2, monstrosity]
    for monster in monsters:
        if paladin.health_points > 0:
            result = paladin.combat(monster)
            if result:
                print("The hero won the combat!")
            else:
                print("The hero lost the combat!")
                break
        else:
            print("The hero has no health points left to fight!")
            break

    if paladin.health_points > 0:
        print("The hero emerges victorious after all combats!")
    else:
        print("The hero has been defeated after fighting all monsters.")
