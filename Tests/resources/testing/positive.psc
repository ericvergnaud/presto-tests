def native print(value:Text):
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

def test "Add two numbers" ():
	a = 3
	b = 8
verifying:
	a + b == 11 
