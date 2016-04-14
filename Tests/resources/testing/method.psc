def native print(value:Text):
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

def Smaller(i1:Integer, i2:Integer):
	return i1 < i2

def test "Method" ():
	a = 3
	b = 8
verifying:
	Smaller(i1 = a, i2 = b)
	