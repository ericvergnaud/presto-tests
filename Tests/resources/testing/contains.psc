def native print(value:Text):
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

def test "Contains condition" ():
	a = [3..8]
	b = 6
verifying:
	b in a
