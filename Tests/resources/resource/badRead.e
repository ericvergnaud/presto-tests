define name as: Text attribute

define Entity as: category with attribute: name
	
define main as: method receiving: Text{} options doing:
	s = read from Entity with "test" as name
