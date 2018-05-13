using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DoorController : MonoBehaviour {

    Animator anim;

    private void OnTriggerEnter(Collider obj)
    {
        anim.SetBool("IsOpening", true);
    }

    private void OnTriggerExit(Collider obj)
    {
        anim.SetBool("IsOpening", false);
    }

    // Use this for initialization
    void Start () {

        anim = this.transform.parent.GetComponent<Animator>();
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
