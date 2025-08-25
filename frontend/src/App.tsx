import {
  Text,
  Box,
  Input,
  Button,
  HStack,
  VStack,
  Image,
  Container,
  Timeline,
} from "@chakra-ui/react";
import "./app.css";
import { useForm } from "react-hook-form";
import logo from "./assets/logo.png";
import { keyframes } from "@emotion/react";
import { useState } from "react";
import { LuShip } from "react-icons/lu";
import axios from "axios";

function App() {
  const slideDown = keyframes`
    from { opacity: 0; transform: translateY(-100px); }
    to   { opacity: 1; transform: translateY(0); }
  `;

  const fadeInUp = keyframes`
    from { opacity: 0; transform: translateY(30px); }
    to   { opacity: 1; transform: translateY(0); }
  `;

  const { register, handleSubmit, reset } = useForm();
  const { register: registerCheck, handleSubmit: handleCheckSubmit } =
    useForm();

  const [showForm, setShowForm] = useState(false);
  const [showCheck, setShowCheck] = useState(false);
  const [statusTimeline, setStatusTimeline] = useState([]);
  const [createdPackageId, setCreatedPackageId] = useState(null);

  const onSubmit = async (data) => {
    const payload = {
      receiver: {
        address: {
          street: data.receiver?.address?.street || "",
          streetNumber: data.receiver?.address?.streetNumber || "",
          city: data.receiver?.address?.city || "",
          code: data.receiver?.address?.code || "",
        },
        firstName: data.receiver?.firstName || "",
        lastName: data.receiver?.lastName || "",
        email: data.receiver?.email || "",
        phoneNumber: data.receiver?.phoneNumber || "",
      },
      sender: {
        firstName: data.sender?.firstName || "",
        lastName: data.sender?.lastName || "",
        email: data.sender?.email || "",
        phoneNumber: data.sender?.phoneNumber || "",
      },
      height: data.height ? Number(data.height) : 0,
      width: data.width ? Number(data.width) : 0,
      depth: data.depth ? Number(data.depth) : 0,
      weight: data.weight ? Number(data.weight) : 0,
    };

    try {
      const res = await axios.post(
        "http://localhost:8080/api/package",
        payload,
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      console.log("Package created:", res.data);

      if (res.data) {
        setCreatedPackageId(res.data);
      }

      reset();
    } catch (err) {
      console.error("Error creating package:", err);
    }
  };

  // Submit for Check Package
  const onCheckSubmit = async ({ packageId }) => {
    console.log("Checking package:", packageId);

    try {
      const res = await axios.get("http://localhost:8084/api/package", {
        params: { packageId },
      });

      const timelineData = res.data;

      setStatusTimeline(timelineData);

      console.log("Package timeline:", timelineData);
    } catch (err) {
      console.error("Error fetching package status:", err);

      if (err.response) {
        alert(`Error: ${err.response.status} - ${err.response.data}`);
      } else if (err.request) {
        alert("No response from server. Check if backend is running.");
      } else {
        alert("Something went wrong. Please try again.");
      }
    }
  };

  return (
    <Box
      minH="100vh"
      bg="white"
      display="flex"
      alignItems="center"
      justifyContent="center"
      color={"black"}
    >
      <Container maxW="md" textAlign="center">
        <VStack spacing={8}>
          {/* Logo */}
          <Box
            animation={`${slideDown} 0.8s ease-out`}
            display="flex"
            alignItems="center"
            justifyContent="center"
            position="relative"
            _before={{
              content: '""',
              position: "absolute",
              top: 0,
              left: 0,
              right: 0,
              bottom: 0,
              bg: "gradient(to-r, blue.400, purple.500)",
              borderRadius: "2xl",
              filter: "blur(20px)",
              opacity: 0.3,
              zIndex: -1,
            }}
          >
            <Image src={logo} w="420px" h="auto" />
          </Box>

          {/* Welcome Text */}
          <Text
            fontSize="2xl"
            fontWeight="600"
            animation={`${slideDown} 0.8s ease-out 0.2s both`}
            letterSpacing="tight"
          >
            Send parcel with our services!
          </Text>

          {/* Buttons */}
          <HStack
            animation={`${slideDown} 0.8s ease-out 0.4s both`}
            flexWrap="wrap"
            justify="center"
          >
            <Button
              size="lg"
              bg="gradient(to-r, blue.500, blue.600)"
              px={8}
              py={6}
              borderRadius="xl"
              fontWeight="600"
              fontSize="md"
              shadow="lg"
              _hover={{
                transform: "translateY(-2px)",
                shadow: "xl",
                bg: "gradient(to-r, blue.600, blue.700)",
              }}
              _active={{ transform: "translateY(0)" }}
              transition="all 0.2s ease"
              onClick={() => {
                setShowForm((prev) => !prev);
                setShowCheck(false);
              }}
            >
              Ship Package
            </Button>

            <Button
              size="lg"
              px={8}
              py={6}
              borderRadius="xl"
              fontWeight="600"
              fontSize="md"
              shadow="md"
              _hover={{
                transform: "translateY(-2px)",
                shadow: "lg",
                borderColor: "gray.300",
                bg: "gray.50",
              }}
              _active={{ transform: "translateY(0)" }}
              transition="all 0.2s ease"
              onClick={() => {
                setShowCheck((prev) => !prev);
                setShowForm(false);
              }}
            >
              Check Package
            </Button>
          </HStack>

          {/* Ship Package Form */}
          {showForm && (
            <Box
              as="form"
              onSubmit={handleSubmit(onSubmit)}
              w="100%"
              p={6}
              borderRadius="xl"
              shadow="md"
              bg="white"
              animation={`${fadeInUp} 0.6s ease-out`}
            >
              {/* Display created package ID */}
              {createdPackageId && (
                <Text mb={4} fontWeight="600">
                  Package ID: {createdPackageId}
                </Text>
              )}

              <VStack spacing={6} align="stretch">
                {/* Receiver Section */}
                <Box textAlign="left">
                  <Text fontSize="lg" fontWeight="600" mb={2}>
                    Receiver Information
                  </Text>
                  <VStack spacing={3} align="stretch">
                    <Input
                      placeholder="First Name"
                      {...register("receiver.firstName", { required: true })}
                    />
                    <Input
                      placeholder="Last Name"
                      {...register("receiver.lastName", { required: true })}
                    />
                    <Input
                      placeholder="Email"
                      type="email"
                      {...register("receiver.email", { required: true })}
                    />
                    <Input
                      placeholder="Phone Number"
                      {...register("receiver.phoneNumber", { required: true })}
                    />
                    <Input
                      placeholder="Street"
                      {...register("receiver.address.street", {
                        required: true,
                      })}
                    />
                    <Input
                      placeholder="Street Number"
                      {...register("receiver.address.streetNumber", {
                        required: true,
                      })}
                    />
                    <Input
                      placeholder="City"
                      {...register("receiver.address.city", { required: true })}
                    />
                    <Input
                      placeholder="Postal Code"
                      {...register("receiver.address.code", { required: true })}
                    />
                  </VStack>
                </Box>

                {/* Sender Section */}
                <Box textAlign="left">
                  <Text fontSize="lg" fontWeight="600" mb={2}>
                    Sender Information
                  </Text>
                  <VStack spacing={3} align="stretch">
                    <Input
                      placeholder="First Name"
                      {...register("sender.firstName", { required: true })}
                    />
                    <Input
                      placeholder="Last Name"
                      {...register("sender.lastName", { required: true })}
                    />
                    <Input
                      placeholder="Email"
                      type="email"
                      {...register("sender.email", { required: true })}
                    />
                    <Input
                      placeholder="Phone Number"
                      {...register("sender.phoneNumber", { required: true })}
                    />
                  </VStack>
                </Box>

                {/* Package Section */}
                <Box textAlign="left">
                  <Text fontSize="lg" fontWeight="600" mb={2}>
                    Package Information
                  </Text>
                  <VStack spacing={3} align="stretch">
                    <Input
                      placeholder="Height (cm)"
                      type="number"
                      {...register("height", {
                        required: true,
                        valueAsNumber: true,
                      })}
                    />
                    <Input
                      placeholder="Width (cm)"
                      type="number"
                      {...register("width", {
                        required: true,
                        valueAsNumber: true,
                      })}
                    />
                    <Input
                      placeholder="Depth (cm)"
                      type="number"
                      {...register("depth", {
                        required: true,
                        valueAsNumber: true,
                      })}
                    />
                    <Input
                      placeholder="Weight (kg)"
                      type="number"
                      {...register("weight", {
                        required: true,
                        valueAsNumber: true,
                      })}
                    />
                  </VStack>
                </Box>

                <Button
                  type="submit"
                  colorScheme="blue"
                  size="lg"
                  borderRadius="xl"
                  fontWeight="600"
                >
                  Submit
                </Button>
              </VStack>
            </Box>
          )}

          {/* Check Package Input + Timeline */}
          {showCheck && (
            <Box
              w="100%"
              p={6}
              borderRadius="xl"
              shadow="md"
              bg="white"
              animation={`${fadeInUp} 0.6s ease-out`}
            >
              {/* Package ID Input */}
              <Box as="form" onSubmit={handleCheckSubmit(onCheckSubmit)} mb={6}>
                <VStack spacing={4} align="stretch">
                  <Box textAlign="left">
                    <Text mb={1} fontWeight="500" fontSize="sm">
                      Enter Package ID
                    </Text>
                    <Input
                      {...registerCheck("packageId", { required: true })}
                    />
                  </Box>
                  <Button
                    type="submit"
                    size="lg"
                    borderRadius="xl"
                    fontWeight="600"
                  >
                    Track Package
                  </Button>
                </VStack>
              </Box>

              {statusTimeline.length > 0 && (
                <Timeline.Root maxW="100%">
                  {statusTimeline.map((item) => (
                    <Timeline.Item key={item.id}>
                      <Timeline.Connector>
                        <Timeline.Separator />
                        <Timeline.Indicator>
                          <LuShip />
                        </Timeline.Indicator>
                      </Timeline.Connector>
                      <Timeline.Content>
                        <Timeline.Title>{item.eventType}</Timeline.Title>
                        <Timeline.Description>
                          {item.timestamp}
                        </Timeline.Description>
                        <Text textStyle="sm">{item.description}</Text>
                      </Timeline.Content>
                    </Timeline.Item>
                  ))}
                </Timeline.Root>
              )}
            </Box>
          )}
        </VStack>
      </Container>
    </Box>
  );
}

export default App;
