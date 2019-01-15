package org.etriks.security.register



class BioasterMemberAccount {

    def String firstName;
    def String lastName;
    def String password;

    def String email;
    def String confirmPassword;

    def String organization;
    def String groups;
    def String hostingValidator;
    def String bioasterValidator;
    def Boolean bioasterValidatorDecision;
    def Date dateCreated;



    static constraints = {
        firstName(blank:false,maxSize:40)
        lastName(blank:false,maxSize:40)
        email(blank:false,email:true,maxSize:80)
        //The custom validation check if the confirmpassword equals the password or not
        password(blank:false,password:true,maxSize:40,minSize:8)
        confirmPassword(blank:false,password:true,maxSize:40,minSize:8,validator:{confirmPassword,obj ->
            def password=obj.properties['password']
            if(password==null) return false
            if(password!=confirmPassword) return false
            else return true

        })

        organization(nullable: false)
        groups(nullable: false)
        bioasterValidator(nullable: false)
        hostingValidator(nullable: false)


    }
}
