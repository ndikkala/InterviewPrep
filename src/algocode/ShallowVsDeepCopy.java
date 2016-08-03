package algocode;

public class ShallowVsDeepCopy implements Cloneable {
	private String name;
	private ObjectAttribute objAttr;
	
 public static void main(String[] args) throws Exception{
	 ObjectAttribute o1 = new ObjectAttribute("Infiniti");
	 ShallowVsDeepCopy s1 =  new ShallowVsDeepCopy("s1", o1);
	 System.out.println("before clone s1 " + s1.name + " "+ s1.objAttr.getName());
	//Shallow Copy using clone
	 ShallowVsDeepCopy s2 =  (ShallowVsDeepCopy)s1.clone();
	 System.out.println("after clone s2 " + s2.name + " "+ s2.objAttr.getName());
	 System.out.println("after clone s1 " + s1.name + " "+ s1.objAttr.getName());
	 /*setName(s2);
	 System.out.println("after setname s2 " + s2.name);
	 System.out.println("after setname s1 " + s1.name);*/
	 
	 //Only primitive variable gets copied and not the object, 
	 //so primitive variable of original doesnt change but the object variable changes.
	 s2.name = "s2";
	 s2.objAttr.setName("Nissan");
	 System.out.println("after setname s2 " + s2.name + " "+ s2.objAttr.getName());
	 System.out.println("after setname s1 " + s1.name+ " "+ s1.objAttr.getName());
	 System.out.println();
	 /*
	  * Shallow copy using assignment operator
	  */
	 ObjectAttribute o2 = new ObjectAttribute("Honda");
	 ShallowVsDeepCopy s3 =  new ShallowVsDeepCopy("s3", o2);
	 System.out.println("before clone s3 " + s3.name + " "+ s3.objAttr.getName());
	 ShallowVsDeepCopy s4 =  s3;
	 System.out.println("after assignment s3 " + s3.name + " "+ s3.objAttr.getName());
	 System.out.println("after assignment s4 " + s4.name + " "+ s4.objAttr.getName());
	 //Only primitive variable gets copied and not the object, 
	 //so primitive variable of original doesnt change but the object variable changes.
	 s4.name = "s4";
	 s4.objAttr.setName("Toyota");
	 System.out.println("after setname s4 " + s4.name + " "+ s4.objAttr.getName());
	 System.out.println("after setname s3 " + s3.name + " "+ s3.objAttr.getName());
	 System.out.println();
	 /*
	  * Deep copy: copy eveyrthing individually, two different objects and two different references. Duplicate object.
	  */
	 ObjectAttribute o3 = new ObjectAttribute("Benz");
	 ShallowVsDeepCopy s5 =  new ShallowVsDeepCopy("s5", o3);
	 ObjectAttribute o4 = new ObjectAttribute(o3.getName());
	 ShallowVsDeepCopy s6 =  new ShallowVsDeepCopy(s5.name, o4);
	 System.out.println("after deepcopy s5 " + s5.name + " "+ s5.objAttr.getName());
	 System.out.println("after deepcopy s6 " + s6.name + " "+ s6.objAttr.getName());
	 s6.name = "s6";
	 s6.objAttr.setName("Jaguar");
	 System.out.println("after setname s6 " + s6.name + " "+ s6.objAttr.getName());
	 System.out.println("after setname s5 " + s5.name + " "+ s5.objAttr.getName());
	 
 }
 
 public ShallowVsDeepCopy(String name, ObjectAttribute objAttr) {
	this.name = name;
	this.objAttr = objAttr;
}
 
 public static void setName(ShallowVsDeepCopy temps){
	temps.name = "will this work";
 }
}

class ObjectAttribute{
	private String name;
	public ObjectAttribute(String name){
		this.name = name;
	}
	public void setName(String anotherName){
		name = anotherName;
	}
	
	public String getName(){
		return this.name;
	}
}
