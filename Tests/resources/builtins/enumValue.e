define Entity as: enumerated Text with symbols:
	ENTITY_1 with "E1" as value
	ENTITY_2 with "E2" as value

define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define main as: method receiving: Text{} options doing:
	print "value=" + ENTITY_1.value