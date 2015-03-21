define Entity as: enumerated Integer with symbols:
	ENTITY_1 with 123 as value
	ENTITY_2 with 456 as value

define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define main as: method receiving: Text{} options doing:
	print "/a=" + ENTITY_1
	print "/b=" + ENTITY_2
	
	
