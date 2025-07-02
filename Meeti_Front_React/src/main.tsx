import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {ReactQueryDevtools} from "@tanstack/react-query-devtools";
import {ToastContainer} from "react-toastify";
import './index.css'
import RouterApp from "./RouterApp";

const queryClient = new QueryClient();
createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <QueryClientProvider client={queryClient}>
          <RouterApp/>
          <ToastContainer/>
          <ReactQueryDevtools/>
      </QueryClientProvider>
  </StrictMode>,
)
