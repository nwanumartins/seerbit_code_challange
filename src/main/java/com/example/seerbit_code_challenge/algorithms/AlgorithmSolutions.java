package com.example.seerbit_code_challenge.algorithms;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AlgorithmSolutions {

      public ArrayList<ParamDto> mergeIntervals(ArrayList<ParamDto> v) {

            if (v == null || v.size() == 0) {
                return null;
            }

            ArrayList<ParamDto> result = new ArrayList<ParamDto>();

            result.add(new ParamDto(v.get(0).first, v.get(0).second));

            for (int i = 1; i < v.size(); i++) {
                int x1 = v.get(i).first;
                int y1 = v.get(i).second;
                int x2 = result.get(result.size() - 1).first;
                int y2 = result.get(result.size() - 1).second;

                if (y2 >= x1) {
                    result.get(result.size() - 1).second = Math.max(y1, y2);
                } else {
                    result.add(new ParamDto(x1, y1));
                }
            }

            return result;
        }

         public int maxSubarrayXOR(int arr[], int n)
           {
               int ans = Integer.MIN_VALUE; // Initialize result

               // Pick starting points of subarrays
               for (int i=0; i<n; i++)
               {
                   // to store xor of current subarray
                   int curr_xor = 0;

                   // Pick ending points of subarrays starting with i
                   for (int j=i; j<n; j++)
                   {
                       curr_xor = curr_xor ^ arr[j];
                       ans = Math.max(ans, curr_xor);
                   }
               }
               return ans;
           }


}
