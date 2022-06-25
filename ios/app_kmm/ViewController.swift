//
//  ViewController.swift
//  app_kmm
//
//  Created by Kareem Radwan on 6/25/22.
//

import UIKit
import shared

// Main Activity
class ViewController: UIViewController , RegisterCallback {
   
    func goToHome() {
        
    }
    
    func goToVerifyPhoneCode() {
        
    }
    
    func onLoading(status: Bool) {
        progress.isHidden = !status
        
    }
    
    func onRegisterError(message: String) {
        
    }
    

    @IBOutlet weak var progress: UIActivityIndicatorView!
    
    let registerController  = RegisterController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        progress.isHidden  = true 
        registerController.callback = self
        // Do any additional setup after loading the view.
    }


    @IBAction func register(_ sender: Any) {
        registerController.register(request: RegisterRequest(email: "kardseem@gmail.com", password: "Password1!", password_confirmation: "Password1!", role: "job_seeker", udid: "Dsadsa"))
        
    }
}

