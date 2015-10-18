def native print(value:Text):
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

attr name(Text):
	pass
	
attr text(Text):
	pass

enum Error(name, text):
	DIVIDE_BY_ZERO(text="Divide by zero!")
	INDEX_OUT_OF_RANGE(text="Index out of range!")
	NULL_REFERENCE(text="Null reference!")

def test "Divide two numbers" ():
	a = 3 / 0
	b = 8
verifying: INDEX_OUT_OF_RANGE
