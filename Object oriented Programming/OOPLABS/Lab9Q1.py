class NewClass(object):
   def __init__(self, param_int=1):
       self.the_int = param_int
       if param_int % 2 == 0:
           self.parity = 'even'
       else:
           self.parity = 'odd'


   def process(self, instance):
       sum_int = self.the_int + instance.the_int
       if sum_int < 0:
           return 'negative'
       elif sum_int % 2 == 0:
           return 'even'
       else:
           return 'odd'


   def __str__(self):
       return 'Value {} is {}'.format(self.the_int, self.parity)




inst1 = NewClass(4)
inst2 = NewClass(-5)
inst3 = NewClass()
print(inst1)  # Line 1
print(inst1.parity)  # Line 2
print(inst1.process(inst2))  # Line 3
print(inst3.process(inst1))  # Line 4
