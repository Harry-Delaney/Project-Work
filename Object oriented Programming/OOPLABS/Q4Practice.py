class Module:
    def __init__(self, name, time, year, location, lecturer):
        """Initialize the module with its name, schedule, and details."""
        self.name = name
        self.time = time
        self.year = year
        self.location = location
        self.lecturer = lecturer
        # Initialize the assignments dictionary (assignment_name: (deadline, percentage))
        self.assignments = {}

    def add_assignment(self, assignment_name, deadline, percentage):
        """Add an assignment with its deadline and percentage weight."""
        self.assignments[assignment_name] = (deadline, percentage)

    def update_assignment(self, assignment_name, deadline=None, percentage=None):
        """Update the details of an existing assignment."""
        if assignment_name in self.assignments:
            current_deadline, current_percentage = self.assignments[assignment_name]
            new_deadline = deadline if deadline else current_deadline
            new_percentage = percentage if percentage else current_percentage
            self.assignments[assignment_name] = (new_deadline, new_percentage)
        else:
            print(f"Assignment '{assignment_name}' not found in this module.")

    def __str__(self):
        """Return a string representation of the module."""
        assignment_str = "\n".join([f"{name}: Deadline - {deadline}, Weight - {percentage}%"
                                   for name, (deadline, percentage) in self.assignments.items()])
        return (f"Module: {self.name}\n"
                f"Time: {self.time}\n"
                f"Year: {self.year}\n"
                f"Location: {self.location}\n"
                f"Lecturer: {self.lecturer}\n"
                f"Assignments:\n{assignment_str if assignment_str else 'No assignments yet'}")


class Student:
    def __init__(self, name):
        """Initialize the student with a name and an empty list of modules."""
        self.name = name
        self.modules = []

    def add_module(self, module):
        """Add a module to the student's list of modules."""
        self.modules.append(module)

    def __str__(self):
        """Return a string representation of the student and their modules."""
        modules_str = "\n\n".join([str(module) for module in self.modules])
        return f"Student: {self.name}\nModules:\n{modules_str if modules_str else 'No modules enrolled yet'}"


# Example Usage

# Create instances of the Module class
module1 = Module(name="Computer Science 101", time="Monday 10:00 AM", year=2024, location="Room 202", lecturer="Dr. Smith")
module1.add_assignment("Assignment 1", "2024-12-10", 30)
module1.add_assignment("Midterm", "2025-01-15", 40)

module2 = Module(name="Math 101", time="Wednesday 2:00 PM", year=2024, location="Room 303", lecturer="Dr. Johnson")
module2.add_assignment("Assignment 1", "2024-12-12", 25)
module2.add_assignment("Final Exam", "2025-02-01", 50)

# Create an instance of the Student class
student = Student(name="John Doe")

# Add modules to the student
student.add_module(module1)
student.add_module(module2)

# Display the student's information
print(student)

# Update an assignment's details
module1.update_assignment("Assignment 1", deadline="2024-12-15", percentage=35)

# Display the updated student's information
print("\nAfter updating assignment:")
print(student)
