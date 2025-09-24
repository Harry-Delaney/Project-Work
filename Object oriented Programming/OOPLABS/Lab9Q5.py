class Attendee:
    total_attendees = 0  # Class-level variable shared by all instances

    def __init__(self, name):
        """Initialize the attendee and increment the total attendees."""
        self.name = name
        Attendee.total_attendees += 1  # Increment the total_attendees for every new instance
        self._welcome_message()  # Call the method to display the welcome message

    def _welcome_message(self):
        """Print a welcome message with the current total number of attendees and the user's name."""
        print(f"Welcome {self.name} to our meeting! There are currently {Attendee.total_attendees} attendees!")

    def leave_meeting(self):
        """Decrease the total number of attendees when someone leaves the meeting."""
        if Attendee.total_attendees > 0:
            Attendee.total_attendees -= 1
            print(f"A person left the meeting. There are now {Attendee.total_attendees} attendees!")
        else:
            print("There are no more attendees!")

    def return_attendees(self):
        """Return the current number of total attendees."""
        return Attendee.total_attendees


# Example usage
attendee1 = Attendee("Alice")  # Alice joins the meeting
attendee2 = Attendee("Bob")    # Bob joins the meeting
attendee3 = Attendee("Charlie")  # Charlie joins the meeting

# Check the total number of attendees
print(f"Total attendees: {attendee1.return_attendees()}")  # Total should be 3

# Simulate someone leaving the meeting
attendee1.leave_meeting()  # Alice leaves the meeting

# Check the total number of attendees again
print(f"Total attendees: {attendee1.return_attendees()}")  # Total should be 2
