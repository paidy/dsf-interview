class Robot:
    """Data model for an intelligent robot."""
    model_no: int
    name: str

    def speak(self, monologue: str):
        """Uses text-to-speech to make the robot speak."""
        pass

    def move(self, location: str):
        """
        Makes the robot move to the specified location. The location will be translated to GPS coordinates.
        """
        pass
