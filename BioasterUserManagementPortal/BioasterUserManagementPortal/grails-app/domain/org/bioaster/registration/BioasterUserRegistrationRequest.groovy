package org.bioaster.registration

class BioasterUserRegistrationRequest {

    def String firstName;
    def String lastName;
    def String password;

    def String email;
    def String confirmPassword;

    def String organization;
    def String validator;
    def Boolean validatorDecision;
    def Date dateCreated;



    static constraints = {
        firstName(blank:false,maxSize:40)
        lastName(blank:false,maxSize:40)
        email(blank:false,email:true,maxSize:80)
        //The custom validation check if the confirmpassword equals the password or not
        password(blank:false,password:true,maxSize:80)
        confirmPassword(blank:false,password:true,maxSize:80,validator:{confirmPassword,obj ->
            def password=obj.properties['password']
            if(password==null) return false
            if(password!=confirmPassword) return false
            else return true

        })

        organization(nullable: false)
        validator(nullable: false)


    }

}
