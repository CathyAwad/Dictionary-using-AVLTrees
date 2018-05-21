public class Tree {
 Node root;
 
    
    int height(Node N){
        if (N == null)
             return 0;
         return N.h;
    }
 
    
    int max(int a, int b){
        return (a > b) ? a : b;
    }
 
     boolean search(Node r, String val){
         boolean found = false;
         while ((r != null) && !found){
             String rval = r.value;
             if (val.compareToIgnoreCase(rval)<0)
                 r = r.left;
             else if (val.compareToIgnoreCase(rval)>0)
                 r = r.right;
             else{
                 found = true;
                 break;
             }
             found = search(r, val);
         }
         return found;
     }
    
     
    Node rotateRight(Node y){
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.h = max(height(y.left), height(y.right)) + 1;
        x.h = max(height(x.left), height(x.right)) + 1;
        return x;
    }
 
       Node rotateLeft(Node x){
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.h = max(height(x.left), height(x.right)) + 1;
        y.h = max(height(y.left), height(y.right)) + 1;
        return y;
    }

       int getBalance(Node N){
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }
 
    Node insert(Node node, String key){
        if (node == null)
            return (new Node(key));
 
        if (key.compareToIgnoreCase(node.value)<0)
            node.left = insert(node.left, key);
        else if (key.compareToIgnoreCase(node.value)>0)
            node.right = insert(node.right, key);
        else {   
        	System.out.println(key + "  already exists... duplicates not allowed");
            return node;
        }        
        node.h = 1 + max(height(node.left),height(node.right));        
        int balance = getBalance(node);
        //System.out.println("balance " + balance);
        if (balance > 1 && key.compareToIgnoreCase(node.left.value)<0)
            return rotateRight(node);
 
        
        if (balance < -1 && key.compareToIgnoreCase(node.right.value)>0)
            return rotateLeft(node);
 
        
        if (balance > 1 && key.compareToIgnoreCase(node.left.value)>0){
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
 
        
        if (balance < -1 && key.compareToIgnoreCase(node.right.value)<0){
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
 
        
        return node;
    }
 
    
    Node minValueNode(Node node){
        Node current = node;
 
        
        while (current.left != null)
           current = current.left;
 
        return current;
    }
 
    Node remove(Node n, String key){
        Boolean found = search(root, key);
        if(!found)
        {
        	System.out.println(key+ " not found");
        	return n;
        }
        	
        if (n == null)
            return n;
 
        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (key.compareToIgnoreCase(n.value)<0)
            n.left = remove(n.left, key);
 
        // If the key to be deleted is greater than the
        // root's key, then it lies in right subtree
        else if (key.compareToIgnoreCase(n.value)>0)
            n.right = remove(n.right, key);
 
        // if key is same as root's key, then this is the node
        // to be deleted
        else
        {
 
            // node with only one child or no child
            if ((n.left == null) || (n.right == null))
            {
                Node temp = null;
                if (temp == n.left)
                    temp = n.right;
                else
                    temp = n.left;
 
                // No child case
                if (temp == null)
                {
                    temp = n;
                    n = null;
                }
                else   // One child case
                    n = temp; // Copy the contents of the non-empty child
            }
            else
            {
            
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node temp = minValueNode(n.right);
 
                // Copy the inorder successor's data to this node
                n.value = temp.value;
 
                // Delete the inorder successor
                n.right = remove(n.right, n.value);
            }
        }
 
        // If the tree had only one node then return
        if (n == null)
            return n;
 
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        n.h = max(height(n.left), height(n.right)) + 1;
 
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(n);
 
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(n.left) >= 0)
            return rotateRight(n);
 
        // Left Right Case
        if (balance > 1 && getBalance(n.left) < 0)
        {
            n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
 
        // Right Right Case
        if (balance < -1 && getBalance(n.right) <= 0)
            return rotateLeft(n);
 
        // Right Left Case
        if (balance < -1 && getBalance(n.right) > 0)
        {
            n.right = rotateRight(n.right);
            return rotateLeft(n);
        }
 
        return n;
    }
 
    // A utility function to print preorder traversal of
    // the tree. The function also prints height of every
    // node
    void preOrder(Node node)
    {
        if (node != null)
        { 
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
   void printLevelOrder()
    {
        int h = height(root);
        int i;
        for (i=1; i<=h; i++)
            printGivenLevel(root, i);
    }
   void printGivenLevel (Node root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.value + " ");
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }
      void inorder(Node r)
     {
         if (r != null)
         {
             inorder(r.left);
             System.out.print(r.value +"  |  ");
             inorder(r.right);
         }
     }
        int countNodes(Node r)
     {
         if (r == null)
             return 0;
         else
         {
             int l = 1;
             l += countNodes(r.left);
             l += countNodes(r.right);
             return l;
         }
     }




}