def native print(value:Text):
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

def Smaller(a:Integer, b:Integer):
	return a < b

def test "Method" ():
	a = 3
	b = 8
expecting:
	Smaller(a = a, b = b)
	